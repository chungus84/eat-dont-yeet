import axios from "axios"
import { authHeader } from "./authHeaders"

export const getRecipes = async () => {
    try {
        const res = await axios.get(`${import.meta.env.VITE_PROFILE_URI}/recipes/all`, { headers: authHeader() })
        if (res.status == 200 && res?.data) {
            return { data: res.data, status: res.status };
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

export const getRecipeDetails = async recipeId => {
    try {
        const res = await axios.get(`${import.meta.env.VITE_PROFILE_URI}/recipes/${recipeId}`, { headers: authHeader() })
        if (res.status === 200 && res?.data) {
            return { data: res.data, status: res.status }
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
