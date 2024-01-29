import axios from "axios"
import { authHeader } from "./authHeaders";

let axiosInstance = axios.create();


export const loginUser = async userCredentials => {

    try {
        const loginRes = await axios.post(`${import.meta.env.VITE_AUTHURL}/users/login`,
            userCredentials,
            {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": "",
                    "Access-Control-Allow-Origin": "*"
                }
            });

        if (!loginRes.headers?.userid && !loginRes.status !== 200) {
            throw new Error("Credentials do not match")
        }
        const userId = loginRes.headers.userid
        const token = loginRes.headers.authorization
        const userName = loginRes.headers.username

        const userObj = {
            "userId": userId,
            "userName": userName,
            "token": token
        }
        localStorage.setItem(`user`, JSON.stringify(userObj))


        return { status: loginRes.status, userId: userId }


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

export const findProfileByUserId = async userId => {

    try {
        const profileRes = await axios.get(`${import.meta.env.VITE_PROFILE_URI}/profile/user/${userId}`, { headers: authHeader() });
        if (profileRes.data?.userName) {
            return { data: profileRes.data, status: profileRes.status }
        }
    } catch (err) {
        return {
            status: err.response?.status,
            error: {
                type: `get`,
                message: err.response?.message
            }
        }
    }
}

export const signUpUser = async user => {
    try {
        const res = await axios.post(`${import.meta.env.VITE_AUTHURL}/users/register`, user, { headers: authHeader() })
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
