import '../css/Register.css';
import {useEffect, useState} from "react";

//TODO: sent data to the database, get data from database
function RegisterForEveryUser(props) {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthOfDate, setBirthOfDate] = useState('');
    const [userType, setUserType] = useState('');
    const [password, setPassword] = useState('');
    const [passwordAgain, setPasswordAgain] = useState('');
    const [email, setEmail] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = props.navigate;
    const userTypeData = props.userTypeData
    const previousDataHandler = props.previousDataHandler;
    const currentData = {firstName, lastName, email, birthOfDate, password, userType};

    const handleSubmit = (event) => {
        event.preventDefault();
        errorMessageSetter();
    }

    const errorMessageSetter = () => {
        if (password !== passwordAgain) {
            setErrorMessage("The passwords do not match!\n Try again!")
        } //set to email
        else if (password !== passwordAgain && email === email) {
            setErrorMessage("The passwords do not match and the email address is already in use!\n Try again!")
        }
        else {
            previousDataHandler(currentData);
            if (userType === 'USER') {
                setErrorMessage('');
                navigate('/');
            }
            else {
                props.userTypeHandler(userType);
                setUserType(userType)
            }
        }
    }

return (
        <div className="register-panel">
            <br/>
            <h1>Registration</h1>
            {{errorMessage} ?
                <p id="wrongLogin">{errorMessage}</p> : null}
            <hr/>
            <form onSubmit={handleSubmit}>
                <input type="text" id="firstName" name="firstName" placeholder="First Name"
                       value={firstName}
                       onChange={(e) => setFirstName(e.target.value)}
                       required/>
                <input type="text" id="lastName" name="lastName" placeholder="Last Name"
                       value={lastName}
                       onChange={(e) => setLastName(e.target.value)}
                       required/>
                <input type="email" id="email" name="email" placeholder="Email address"
                       value={email}
                       onChange={(e) => setEmail(e.target.value)}
                       required/>
                <input type="date" id="userBirthOfDate" name="userBirthOfDate" placeholder="Birth of date"
                       value={birthOfDate}
                       onChange={(e) => setBirthOfDate(e.target.value)}
                       required/>
                <div className="password">
                    <input type="password" id="password" name="password" placeholder="Password"
                           value={password}
                           onChange={(e) => setPassword(e.target.value)}
                           required/>
                    <input type="password" id="passwordAgain" name="passwordAgain" placeholder="Password again"
                           value={passwordAgain}
                           onChange={(e) => setPasswordAgain(e.target.value)}
                           required/>
                </div>
                {userTypeData.map((user) =>
                    <span className="userType">
                            <input type="radio" value={user} id={user} name="groupType"
                                   onChange={(e) => setUserType(e.target.value)}
                                   required/> {user}
                        </span>
                )}
                <button type="submit" value="Submit">Submit</button>
            </form>

            <div className="mainPageHref">
                <a href="/">
                    <button type="button" value="Main page">Main page</button>
                </a>
            </div>
        </div>
    )


}

export default RegisterForEveryUser;