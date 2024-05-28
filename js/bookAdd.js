document.addEventListener('DOMContentLoaded', () => {
    const bookList = document.getElementById('book-list');
    const bookList1 = document.getElementById('book-list1');
    const bookList2 = document.getElementById('book-list2');
    const addBookForm = document.getElementById('addbook');

    const baseUrl = 'http://localhost:8009/books' 
  
    // Function to fetch books from the backend
    async function fetchBooks() {
      try {
        const response = await fetch(baseUrl); // actual backend endpoint
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const books = await response.json();   // it wait till response not find the value wheather success or error
                                              // but wait only for fetchBook fxn not for whole program and "await is only can use with "
        // displayBooks(books);
        // displayBookInUL(books);
        displayBookInTable(books);
        console.log(response);
      } catch (error) {
        console.error('Error fetching books:', error);
        bookList2.innerHTML = '<tr><td colspan="4">Error loading books. Please try again later.</td></tr>';
      }
    }
  
    // Function to display books on the page
    // function displayBooks(books) {
    //   bookList.innerHTML = '';
    //   books.forEach(book => {
    //     const bookDiv = document.createElement('div');
    //     bookDiv.className = 'book';
    //     bookDiv.innerHTML = `
    //       <h2>${book.title}</h2>
    //       <p><strong>Author:</strong> ${book.authorName}</p>
    //       <p><strong>ISBN:</strong> ${book.isbn}</p>
    //       <p><strong>Publication Year:</strong> ${book.publicationYear}</p>
    //     `;
    //     bookList.appendChild(bookDiv);
    //   });
    // }


    // // dispaly book in unordered list
    // function displayBookInUL(books){
    //     bookList1.innerHTML='';  // clearing the existing list
    //     books.forEach(book => {
    //         const bookUL = document.createElement('ul');
    //         bookUL.className='book';
    //         bookUL.innerHTML = `
    //             <h2> ${book.title}</h2>
    //             <p><strong>Author:</strong> ${book.authorName}</p>
    //             <p><strong>ISBN:</strong> ${book.isbn}</p>
    //             <p><strong>Publication Year:</strong> ${book.publicationYear}</p>
    //         `;

    //         bookList1.appendChild(bookUL);
    //     });
    // }

    // displaying books in table form
    function displayBookInTable(books){
        bookList2.innerHTML='';  // clearing the existing row
        books.forEach( book=>{
            const bookRow = document.createElement('tr');
            bookRow.className = 'bookRow';
            bookRow.innerHTML=`
                <td>${book.title}</td>
                <td>${book.authorName}</td>
                <td>${book.publicationYear}</td>
                <td>${book.isbn}</td>
            `;

            bookList2.appendChild(bookRow);
        });
    }

    //Add book in libaray

    async function addBook(title, authorName, publicationYear, isbn){
        try{
            const response = await fetch(baseUrl ,{
                method:'POST',
                headers:{
                    'Content-Type':'application/json'
                },
                body:JSON.stringify({title, authorName, publicationYear, isbn})
            });

            let newBook;
            try{
                newBook = await response.json();
                console.log("Book added successfully", newBook);

                // navigate to display book list screen
                window.location.href = 'Book.html';
            }catch(jsonError){
                //if parsing fails, log the response text
                const responseText = await response.text();
                console.error('Error adding book: ', jsonError, responseText);
            }
            
        }
        catch(error){
            console.error('Error adding book:', error);
        }
    }

    //Event listener for form submission

    if(addBookForm){

        addBookForm.addEventListener('submit', (e)=>{
            e.preventDefault();
            const title = document.getElementById('title').value;
            const authorName = document.getElementById('author').value;
            const publicationYear = document.getElementById('year').value;
            const isbn = document.getElementById('isbn').value;
            console.log('Form values: ', {title, authorName, publicationYear, isbn});
            addBook(title, authorName, publicationYear, isbn);
            addBookForm.reset(); // after adding 
        });

    }
    
 
    // Fetch and display books on page load
    if(bookList2){
        fetchBooks();
    }
  });
  