import { useState, useEffect } from "react"
import LoginForm from "./utils/LoginForm";

import PropTypes from 'prop-types';

const Home = ({ submitAction }) => {

    const [login, setLogin] = useState(false)

    const loginClicked = () => {
        setLogin(true);
    }

    const signUpClicked = () => {
        setLogin(false)
    }


    return (
        <>
            <div>
                <h1>Welcome to eatDON'Tyeet</h1>
            </div>
            <div>
                <h2>Please sign-up or login</h2>
                {login && (
                    <>
                        <h3>Login</h3>
                        <button className={"btn btn-primary rounded-pill"} onClick={signUpClicked}>Sign Up</button>
                    </>
                )}
                {!login && (
                    <>
                        <h3>Sign Up</h3>
                        <button className={"btn btn-primary rounded-pill"} onClick={loginClicked}>Login</button>
                    </>

                )}
                {login && (
                    <LoginForm submitAction={submitAction} />
                )}

            </div>
        </>

    )
}

Home.propTypes = {
    submitAction: PropTypes.func.isRequired
}

export default Home
