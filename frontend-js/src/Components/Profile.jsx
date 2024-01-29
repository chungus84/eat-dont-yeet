import React from 'react'
import { useNavigate } from 'react-router-dom'

const Profile = ({ data }) => {

    const navigate = useNavigate();
    return (
        <>
            <h2>Hi {data.userName}</h2>

            <button className="btn btn-primary" onClick={(() => navigate("/recipe/all"))}>Find Recipes</button>

            <h2>Recipes saved to profile</h2>

        </>



    )
}

export default Profile
