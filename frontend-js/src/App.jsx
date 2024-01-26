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
        console.log(externalDataCallResult);
    }



    return (
        <>
            <Header />
            <Routes>
                <Route index element={<Home submitAction={loginHandler} />} />
            </Routes>


        </>
    )
}

export default App
