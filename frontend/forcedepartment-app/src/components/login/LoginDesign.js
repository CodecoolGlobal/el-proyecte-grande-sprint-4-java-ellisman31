import {useEffect, useState} from "react";
import {Link} from 'react-router-dom'

function LoginDesign(props) {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [emailPassword, setEmailPassword] = useState('');
    const getUserData = props.getUserData;
    const navigation = props.navigation;

    const handleSubmit = (e) => {
        e.preventDefault();
        errorMessageSetter();
    }

    //TODO: fetch isEmailIsValid + check fetch
    const errorMessageSetter = () => {
        for (const data of getUserData) {

            if (password !== emailPassword) {
                setErrorMessage("The given password for the email is wrong! \nTry again!")
            } else if (data.email !== email) {
                setErrorMessage("The given email address is not exist!")
            } else {
                navigation("/");
            }
        }
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