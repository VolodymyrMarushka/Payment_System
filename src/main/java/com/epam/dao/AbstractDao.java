package com.epam.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Identificator, K> implements DAO<T, K> {
    private static Logger logger = Logger.getLogger(AbstractDao.class);

    protected abstract String getCreateQuery();

    protected abstract void setStatementForCreate(PreparedStatement pstm, T someObject);

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void setStatementForId(PreparedStatement pstm, int id);

    protected abstract String getSelectByIdQuery();

    protected abstract void setStatementForKey(PreparedStatement pstm, K key);

    protected abstract String getSelectByKeyQuery();

    protected abstract void setStatementForUpdate(PreparedStatement pstm, T object);

    protected abstract String getUpdateQuery();

    protected abstract void setStatementForDelete(PreparedStatement pstm, T object);

    protected abstract String getDeleteQuery();

    protected abstract String getQueryForGetAll();

    protected abstract String getDeleteAllQuery();

    protected abstract String getTableName();


    @Override
    public T create(T someObject) throws DaoException {
        logger.info("Start creating object");
        String insert = getCreateQuery();
        String lastIdQuery = "SELECT LAST_INSERT_ID()";
        try (Connection connection = DBInit.getConection()) {

            PreparedStatement pstm = connection.prepareStatement(insert);
            setStatementForCreate(pstm, someObject);
            pstm.executeUpdate();

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(lastIdQuery);
            rs.next();
            int id = rs.getInt(1);
            someObject.setId(id);
            logger.info("Object seved to DB");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return someObject;
    }

    @Override
    public T readById(int id) throws DaoException {
        logger.info("Getting object by Id");
        String read = getSelectByIdQuery();

        List<T> objects = new ArrayList<>();
        try (Connection connection = DBInit.getConection()) {
            PreparedStatement pstm = connection.prepareStatement(read);
            setStatementForId(pstm, id);
            ResultSet rs = pstm.executeQuery();
            objects = parseResultSet(rs);

            logger.info("Object is got by Id");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        if (objects.isEmpty()) {
            throw new DaoException("Object with id not found!");
        }
        return objects.get(0);
    }

    @Override
    public T readByKey(K key) throws DaoException {
        logger.info("Getting object by Key");
        String query = getSelectByKeyQuery();
        List<T> objects = new ArrayList<>();

        try (Connection cn = DBInit.getConection()) {
            PreparedStatement pstm = cn.prepareStatement(query);
            setStatementForKey(pstm, key);
            ResultSet rs = pstm.executeQuery();
            objects = parseResultSet(rs);
            if (objects.isEmpty()) {
                throw new Exception("Object not found ");
            }
            logger.info("Object is got by key");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }

        return objects.get(0);
    }

    @Override
    public boolean update(T object) throws DaoException {
        logger.info("Updating object");
        String query = getUpdateQuery();
        int status;

        try (Connection connection = DBInit.getConection()) {
            PreparedStatement pstm = connection.prepareStatement(query);
            setStatementForUpdate(pstm, object);
            status = pstm.executeUpdate();
            logger.info("Object is updated");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return status == 1 ? true : false;
    }


    @Override
    public boolean delete(T object) throws DaoException {
        logger.info("Start deleting object");
        String delete = getDeleteQuery();
        int deleted;
        try (Connection cn = DBInit.getConection()) {
            PreparedStatement pstm = cn.prepareStatement(delete);
            setStatementForDelete(pstm, object);
            deleted = pstm.executeUpdate();
            logger.info("Object is deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return deleted == 1 ? true : false;
    }


    @Override
    public List<T> getAll() {

        List<T> objects = new ArrayList<>();
        String query = getQueryForGetAll();

        try (Connection cn = DBInit.getConection()) {
            logger.info("Start getting objects from DB");
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            objects = parseResultSet(rs);
            logger.info("Objects are recived ");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return objects;
    }

    public boolean deleteAll() throws DaoException {
        logger.info("Start deleting all objects!");
        String deleteQuery = getDeleteAllQuery();
        String tableName = getTableName();
        int flag;

        try (Connection cn = DBInit.getConection()) {
            Statement statement = cn.createStatement();
            Statement statement1 = cn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM `" + tableName + "`");
            statement1.executeUpdate("Alter table " + getTableName() + " ALTER COLUMN id RESTART WITH 1");

            rs.next();
            if (rs.getInt(1) == 0) {
                logger.info("Table " + tableName + " is empty");
                return true;
            } else {
                flag = statement.executeUpdate(deleteQuery);
                if (flag != 0) {
                    logger.info("Objects were deleted!");
                } else logger.info("Something wrong with deleting objects!");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return flag != 0 ? true : false;
    }

}
