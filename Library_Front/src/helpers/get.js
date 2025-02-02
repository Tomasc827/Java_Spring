const url = "http://localhost:8080/api/books";

export const getBooks = async () => {
  const response = await fetch(url);
  const data = await response.json();
  return data;
}
