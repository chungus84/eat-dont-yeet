import { useState, useEffect } from 'react'
import { useNavigate, Routes, Route } from 'react-router-dom'
import './App.css'
import Header from './Components/utils/Header'
import Home from './Components/Home'

function App() {

    const [error, setError] = useState({});
    const [profile, setProfile] = useState({});

    const navigate = useNavigate();

    const loginHandler = async userCredentials => {

    }



    return (
        <>
            <Header />
            <Routes>
                <Route index element={<Home />} />
            </Routes>


        </>
    )
}

export default App
