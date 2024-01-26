import { useState, useEffect } from 'react'
import { useNavigate, Routes, Route } from 'react-router-dom'
import './App.css'
import Header from './Componenets/utils/Header'
import Home from './Componenets/Home'

function App() {


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
