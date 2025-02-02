import { useEffect } from "react";
import { getBooks } from "../helpers/get";
import { useBookData } from "./BookContext";

export const Homepage = () => {

    const {error,setError,books,setBooks,update, setUpdate} = useBookData();

    const getAllBooks = async () => {
    try{
      const bookData = await getBooks();
      setBooks(bookData);
    setUpdate((prev) => prev + 1)
    } catch (err) {
        setError(err.message);
        setTimeout(() => {
            setError("");
        },3000)
    }
}

useEffect(() => {
    getAllBooks();
},[]);


  return (
    <>
    <div>
        {books.map((book) => (
            <div key={book.id}>
                <h2>{book.title}</h2>
                <p>{book.author}</p>
                <p>{book.publishingYear}</p>
                <p>{book.condition}</p>
                <p>{book.price}</p>
                <img src={book.imageURL} alt={book.title} />
            </div>
        ))}
    </div>
    </>
  )
}