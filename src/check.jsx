import React, {useState} from "react";
import axios from "axios";

function FecthBooks(){

    const[books, setBook] = useState([]);
    const[loading, setLoading] = useState(false);
    const[error, setError] = useState(null);

    //without use of useEffect hooks
    async function fetchBooksList (){
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get('http://localhost:8009/books');
            setBook(response.data);
            setLoading(false);
            
        } catch (error) {
            setError(error.message);
            setLoading(false);
            
        }
        
    }

    return(
        <div>
            <h1>Book List</h1>
            <button onClick={fetchBooksList}>Book List</button>
            {loading && <p>Books Loading...</p>}
            {error && <p>Error Loading books....</p>}
            {/* <ul>
                {books.map((book) => (
                    <li>key ={book.id}
                        <h2>{book.title}</h2>
                        <p>{book.authorName}</p>
                        <p>{book.publicationYear}</p>
                        <p>{book.isbn}</p>
                    </li>
                ))}
            </ul> */}

            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author Name</th>
                        <th>Publication Year</th>
                        <th>isbn</th>
                    </tr>  
                </thead>

                <tbody>
                        {books.map((book) => (
                            <tr>
                                <td>{book.title}</td>
                                <td>{book.authorName}</td>
                                <td>{book.publicationYear}</td>
                                <td>{book.isbn}</td>
                            </tr>
                        ))}
                    </tbody>
            </table>

        </div>

    );

}

export default FecthBooks;