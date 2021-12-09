import {useState} from "react";
import {Link} from 'react-router-dom'
import axios from 'axios';

function LoginDesign(props) {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigation = props.navigation;
    const Qs = require('qs');

    const handleSubmit = async (e) => {
        e.preventDefault();

        axios.post("http://localhost:8080/api/login",
            Qs.stringify({
            email: email,
            password: password
            }), {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }})
            .then(res => {
                console.log(res);
                if (res.data.accessToken) {
                    localStorage.setItem('token', res.data.accessToken);
                    navigation("/");
                }
                else {
                    setErrorMessage("Wrong email address or password was given! Try again..")
                }
            })
            .catch(err => {
                console.log(err);
            })

    }

    return (
        <div className="login-block">
            <h1>Login</h1>
            {errorMessage ?
                <p id="errorMessage">{errorMessage}</p> : null}
            <form onSubmit={handleSubmit}>
                <input type="email" id="email" name="email" placeholder="Email address"
                       onChange={(e) => setEmail(e.target.value)}
                       required/>
                <input type="password" id="password" name="password" placeholder="Password"
                       onChange={(e) => setPassword(e.target.value)}
                       required/>
                <button type="submit">Submit</button>
            </form>

            <div className="mainPageHref">
                <Link to={"/"}>
                    <button type="button">Main page</button>
                </Link>
                <p>If you don't have an account, press down bellow to register</p>
                <Link to={"/registration"}>
                    <button type="button">Registration</button>
                </Link>
            </div>
        </div>
    )
}


export default LoginDesign;