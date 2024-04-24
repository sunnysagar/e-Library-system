package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.model.Author;
import com.sunny.Book.Library.System.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // creating author
    public void findOrCreateAuthor(String name, String biography){
        Author author = authorRepository.findByName(name);

        if(author == null){
            author = new Author();
            author.setName(name);
            author.setBiography(biography);
            authorRepository.save(author);
        }

    }

    // getting author
    public Author getauthor(long authorId){
        return authorRepository.findById(authorId).get();
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
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
    public void deleteAuthor(long id)
    {
        authorRepository.deleteById(id);
    }


}
