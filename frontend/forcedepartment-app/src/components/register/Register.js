import '../css/Register.css';
import {useEffect, useState} from "react";

//TODO: sent data to the database, get data from database
function Register(props) {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [birthOfDate, setBirthOfDate] = useState('');
    const [password, setPassword] = useState('');
    const [passwordAgain, setPasswordAgain] = useState('');
    const [userType, setUserType] = useState('');
    const userTypesTemp = ["USER", "WORKER"];

    const [errorMessage, setErrorMessage] = useState('');
    const [getData, setGetData] = useState();

    const [sendData, setSendData] = useState({
        firstName: firstName, lastName: lastName, email: email, birthOfDate: birthOfDate, password: password,
        userType: userType
    })

    useEffect(() => {
        document.title = "Registration";
        setErrorMessage('');
    }, []);

    const saveDataIntoTheDatabase = async () => {
        const response = await fetch('', {
            method: "POST",
            body: JSON.stringify(sendData)
        })
    }
    //in the useEffect
    const getUsersFromDatabase = () => {
        fetch(''
        )
            .then((res) => res.json())
            .then((res) => {
                setGetData(res);
            })
    }

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
            setErrorMessage('');
        }
    }

    return (
        <div className="container">
            <div className="register-form">
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
                    {userTypesTemp.map((user) =>
                        <span className="userType">
                            <input type="radio" value={user} id="radio" name="groupType"
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
        </div>
    )
}

export default Register