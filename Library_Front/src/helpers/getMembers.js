const url = "http://localhost:8080/api/members";


export const getMembers = async () => {
    const response = await fetch(url);
    const data = await response.json();
    return data;
}