import { useState, useEffect } from 'react';
import User from './model/user.model.js';


const SignUpForm = ({ submitAction }) => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [userName, setUserName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("")

    const handleSignUp = event => {
        event.preventDefault();
        if (password === repeatPassword) {

            const userToSubmit = new User(firstName, lastName, email, userName, password)
            console.log(userToSubmit);
            submitAction(userToSubmit);
            setFirstName("");
            setLastName("");
            setEmail("");
            setUserName("");
            setPassword("");
            setRepeatPassword("");

        }


    }


    useEffect(() => {

    })


    return (
        <div className="container w-50">
            <form aria-label='form' onSubmit={handleSignUp}>
                <br />
                <div className="form-group" >
                    <input type="text" className='form-control' name="firstName" id="firstName" placeholder='First Name' value={firstName} onChange={event => { setFirstName(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="text" className='form-control' name="LastName" id="LastName" placeholder='Last Name' value={lastName} onChange={event => { setLastName(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="text" className='form-control' name="email" id="email" placeholder='Email' value={email} onChange={event => { setEmail(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="text" className='form-control' name="userName" id="userName" placeholder='Username' value={userName} onChange={event => { setUserName(event.target.value) }} />
                </div>
                <br />

                <div className="form-group">
                    <input type="password" className='form-control' name="password" id="password" placeholder='Password' value={password} onChange={event => { setPassword(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="password" className='form-control' name="repeatPassword" id="repeatPassword" placeholder='repeatPassword' value={repeatPassword} onChange={event => { setRepeatPassword(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="submit" value="Register" className='form-control btn btn-primary rounded-pill' />
                </div>


            </form>

        </div>
    )
}

export default SignUpForm
