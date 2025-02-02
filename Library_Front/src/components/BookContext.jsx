import { createContext, useContext, useState } from "react";
import { useNavigate } from "react-router";

const BookDataContext = createContext();

export const BookProviders = ({children}) => {
    const [books, setBooks] = useState([]);
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const [update,setUpdate] = useState(0);
    const [members,SetMembers] = useState([]);
    const [success, setSuccess] = useState("");

    return (
        <BookDataContext.Provider value={{books, setBooks, error, setError, navigate, update, setUpdate,members,SetMembers,success, setSuccess}}>
            {children}
        </BookDataContext.Provider>
    )
}

export const useBookData = () => useContext(BookDataContext);