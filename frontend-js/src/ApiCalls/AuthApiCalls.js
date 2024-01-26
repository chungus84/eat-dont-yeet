import axios from "axios"


export const loginUser = async userCredentials => {

    try {
        const res = await axios.post(`${import.meta.env.VITE_AUTHURL}/users/login`, userCredentials, { headers: { 'Content-Type': 'application/json', "authorization": "" } });
        console.log(res);
    } catch (err) {
        return {
            status: err.response?.status,
            error: {
                type: `post`,
                message: err.response?.message
            }
        }
    }
}
