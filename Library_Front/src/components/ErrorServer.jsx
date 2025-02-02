import { useState, useEffect } from "react";
import { useBookData } from "./BookContext";

const ErrorServer = () => {
  const { error,  setError } = useBookData();
  const [isVisible, setIsVisible] = useState(false);
 

  useEffect(() => {
    let hideTimeout;
    
    if (error) {
      setIsVisible(false);
   
      
      const showTimeout = setTimeout(() => {
        setIsVisible(true);
        
        hideTimeout = setTimeout(() => {
          setIsVisible(false);
          setTimeout(() => {
            setError("");
      
          }, 500);
        }, 2500);
      }, 50);

      return () => {
        clearTimeout(showTimeout);
        clearTimeout(hideTimeout);
       
      };
    }
  }, [error, setError]);

  if (!error) return null;

  const errorMessage = typeof error === 'string' ? error : 'An error occurred';

  return (
    <div className="relative">
      <div
        className={`z-[100] fixed top-[12%] left-[1%] bg-figma-semi-dark-blue border-l-4 border-r-4 border-figma-red text-figma-red p-6 rounded-3xl shadow-lg shadow-figma-red transition-all duration-500 ease-out ${
          isVisible
            ? "translate-x-0 opacity-100"
            : "-translate-x-full opacity-0"
        }`}
      >
        <p className="font-medium">{errorMessage}</p>
      </div>
    </div>
  );
};

export default ErrorServer;