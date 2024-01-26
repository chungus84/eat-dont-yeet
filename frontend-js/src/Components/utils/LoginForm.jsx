import { useEffect, useState } from 'react';

const LoginForm = () => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return (
        <div className="container w-50">
            <br />
            <form aria-label="form">
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

export default LoginForm
