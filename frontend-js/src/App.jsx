import { useState, useEffect } from 'react';
import { useNavigate, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './Components/utils/Header';
import Home from './Components/Home';

import * as authApi from './ApiCalls/AuthApiCalls';

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
        console.log(user);
        const externalDataCallResult = await authApi.signUpUser(user);
        if (externalDataCallResult?.error) {
            const errorObj = { ...externalDataCallResult.error };
            errorObj.message = `There was a problem: ${externalDataCallResult.error.message}`;
            setError(errorObj)
        }
        console.log(externalDataCallResult);
    }



    return (
        <>
            <Header />
            <Routes>
                <Route index element={<Home submitAction={loginHandler} signUpFunc={signUpHandler} />} />

            </Routes>


        </>
    )
}

export default App
