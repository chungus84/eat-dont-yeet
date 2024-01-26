import { useEffect, useState } from 'react';
import PropTypes from 'prop-types';

const LoginForm = ({ submitAction }) => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = event => {
        event.preventDefault();
        const userCredentials = {
            email: email,
            password: password
        }
        submitAction(userCredentials);
        setEmail('');
        setPassword('');
    }

    return (
        <div className="container w-50">
            <br />
            <form aria-label="form" onSubmit={handleLogin}>
                <div className="form-group">
                    <input type="email" name="email" id="email" className="form-control" placeholder="email" value={email} onChange={event => { setEmail(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="password" name="password" id="password" className='form-control' placeholder='password' value={password} onChange={event => { setPassword(event.target.value) }} />
                </div>
                <br />
                <div className="form-group">
                    <input type="submit" value="Submit" className='form-control btn btn-primary rounded-pill' />
                </div>
            </form>
        </div>
    )
}

LoginForm.propTypes = {
    submitAction: PropTypes.func.isRequired
}

export default LoginForm
