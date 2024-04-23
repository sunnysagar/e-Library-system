package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.model.Author;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.repository.AuthorRepository;
import com.sunny.Book.Library.System.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    BookRepository bookRepository;

//    public AuthorService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }


    // creating author
    public String findOrCreateAuthor(String name, String biography){
        Author author = authorRepository.findByName(name);

        if(author == null){
            author = new Author();
            author.setName(name);
            author.setBiography(biography);
            authorRepository.save(author);
        }

        return "success";
    }

    // getting author
    public Author getauthor(long authorId){
        return authorRepository.findById(authorId).get();
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    // getting all books of particular author by authorName from DB

    public List<Book> getAllBookByAuthorName(String authorName){
        return bookRepository.findByAuthorNameIgnoreCaseContaining(authorName);
    }

    // updating author
    public boolean updateAuthor(long authorId, Author updatAuthor){
        // check author is present or not
        Optional<Author> authorPresent = authorRepository.findById(authorId);

        if(authorPresent.isPresent()){
            // get the existing author
            Author existingAuthor = authorPresent.get();

            // Now update the existing author
            existingAuthor.setName(updatAuthor.getName());
            existingAuthor.setBiography(updatAuthor.getBiography());

            // save the updated one
            authorRepository.save(existingAuthor);
            return true;
        }
        else
            return false;

    }

    // save the author detail through book controller
    public Author saveThroughBook(String name){
        Author author = authorRepository.findByName(name);
        if(author == null){
            author = new Author();
            author.setName(name);
            return authorRepository.save(author);
        }

        return author;
    }

    // deleting author
    public String deleteAuthor(long id)
    {
        authorRepository.deleteById(id);
        return "success";
    }


}
