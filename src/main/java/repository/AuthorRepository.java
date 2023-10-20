package repository;

import entity.AuthorTable;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

import static config.LibraryDatabaseConfiguration.getSession;

public class AuthorRepository {
    Session session;
    public AuthorRepository(){
        session = getSession();
    }

    public List<AuthorTable> findAuthorById(long id) {
        final String hql = """
                SELECT * FROM author where id = :id
                """;

        NativeQuery<AuthorTable> query = session.createNativeQuery(hql, AuthorTable.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<AuthorTable> findAuthorByName(String first_name) {
        final String hql = """
                SELECT * FROM author where first_name = :first_name
                """;

        NativeQuery<AuthorTable> query = session.createNativeQuery(hql, AuthorTable.class);
        query.setParameter("first_name", first_name);
        return query.getResultList();
    }
}
