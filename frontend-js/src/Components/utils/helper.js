export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
}
