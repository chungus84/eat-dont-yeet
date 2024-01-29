
export const authHeader = () => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        return {
            "userId": user.userId,
            "userName": user.userName,
            "Authorization": user.token,
            "Content-Type": "application/json",
            Authorization: user.token

        };
    } else {
        return {};
    }
}
