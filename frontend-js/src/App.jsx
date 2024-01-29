import { useState, useEffect } from 'react';
import { useNavigate, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './Components/utils/Header';
import Home from './Components/Home';
import Profile from './Components/Profile';
import RecipeFeed from './Components/RecipeFeed';

import * as authApi from './ApiCalls/AuthApiCalls';
import * as recipeAPI from './ApiCalls/recipeApiCalls';

import { getCurrentUser } from './Components/utils/helper';

function App() {

    const [error, setError] = useState({});
    const [profile, setProfile] = useState({});

    const navigate = useNavigate();

    const loginHandler = async userCredentials => {

        const externalDataCallResult = await authApi.loginUser(userCredentials);

        if (externalDataCallResult?.error) {
            const errorObj = { ...externalDataCallResult.error };
            errorObj.message = `There was a problem: ${externalDataCallResult.error.message}`;
            setError(errorObj)
        }
        const userId = externalDataCallResult?.userId ? externalDataCallResult.userId : "";
        getProfileHandler(userId);
        navigate(`/profile/${userId}`)


    }

    const getProfileHandler = async userId => {
        const externalDataCallResult = await authApi.findProfileByUserId(userId);
        if (externalDataCallResult?.error) {
            const errorObj = { ...externalDataCallResult.error };
            error.message = `There was a problem: ${externalDataCallResult.error.message}`;
            setError(errorObj)
        }
        const userProfile = externalDataCallResult.data?.userName ? externalDataCallResult.data : {}
        setProfile(userProfile)

    }

    const signUpHandler = async user => {

        const externalDataCallResult = await authApi.signUpUser(user);
        if (externalDataCallResult?.error) {
            const errorObj = { ...externalDataCallResult.error };
            errorObj.message = `There was a problem: ${externalDataCallResult.error.message}`;
            setError(errorObj)
        }
        console.log(externalDataCallResult);
    }

    const getRecipeBank = async () => {
        const externalDataCallResult = await recipeAPI.getRecipes();
        if (externalDataCallResult?.error) {
            const errorObj = { ...externalDataCallResult.error };
            errorObj.message = `There was a problem: ${externalDataCallResult.error.message}`;
            setError(errorObj)
        }
        console.log(externalDataCallResult);
    }

    useEffect(() => {
        if (localStorage.getItem('user')) {
            const currentUser = getCurrentUser();
            getProfileHandler(currentUser.userId);
        }
    }, [])



    return (
        <>
            <Header />
            <Routes>
                <Route index element={<Home submitAction={loginHandler} signUpFunc={signUpHandler} />} />
                <Route path='profile/:id' element={<Profile data={profile} />} />
                <Route path='recipe/all' element={<RecipeFeed recipeFunc={getRecipeBank} />} />

            </Routes>


        </>
    )
}

export default App
