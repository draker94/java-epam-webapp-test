package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Instructor;
import by.training.enums.Ranks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstructorDaoImpl extends BaseDaoImpl implements InstructorDao {
    @Override
    public List<Instructor> getInstructorsList() throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `rank` FROM `instuctors`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
                instructors.add(instructor);
            }
            return instructors;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Instructor getBySurname(String surname) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `role` FROM `instructors` WHERE `surname` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            Instructor instructor = null;
            if (resultSet.next()) {
                instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Instructor entity) throws DaoException {
        String sql = "INSERT INTO `instructors` (`id`, `surname`, `name`, `rank`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getName());
            statement.setLong(4, entity.getRank().getId());
            statement.executeUpdate();
            return entity.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Instructor read(Long id) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `rank` FROM `instructors` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Instructor instructor = null;
            if (resultSet.next()) {
                instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Instructor entity) throws DaoException {
        String sql = "UPDATE `instructors` SET `surname` = ?, `name` = ?, `rank` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, entity.getSurname());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getRank().getId());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `instructors` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = statement.getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}