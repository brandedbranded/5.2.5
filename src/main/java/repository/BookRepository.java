package repository;

import entity.BookTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

import static config.LibraryDatabaseConfiguration.getSession;

public class BookRepository {
    Session session;

    public BookRepository() {
        session = getSession();
    }

    public List<BookTable> findAll() {
        final String hql = """
                SELECT * FROM book
                """;

        return session.createNativeQuery(hql, BookTable.class)
                .getResultList();
    }

    public List<BookTable> findBookByTitle(String bookTitle) {
        final String hql = """
                SELECT * FROM book where book_title = :bookTitle
                """;

        NativeQuery<BookTable> query = session.createNativeQuery(hql, BookTable.class);
        query.setParameter("bookTitle", bookTitle);
        return query.getResultList();
    }

    public void deleteAll() {
        final String hql = "DELETE FROM book" ;

        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql, BookTable.class)
                .executeUpdate();
        tr.commit();
    }

    public void deleteBookByTitle(String bookTitle) {
        final String hql = """
                DELETE FROM book where book_title = :bookTitle
                """;

        Transaction tr = session.beginTransaction();
        NativeQuery<BookTable> query = session.createNativeQuery(hql, BookTable.class);
        query.setParameter("bookTitle", bookTitle).executeUpdate();
        tr.commit();
    }

    public void insertBook(String bookTitle, long authorId) {
        final String hql = """
                INSERT INTO book
                (book_title, author_id)
                VALUES(:bookTitle, :authorId)
                """;

        Transaction tr = session.beginTransaction();
        NativeQuery<BookTable> query = session.createNativeQuery(hql, BookTable.class);
        query.setParameter("bookTitle", bookTitle);
        query.setParameter("authorId", authorId);
        query.executeUpdate();
        tr.commit();
    }
}
