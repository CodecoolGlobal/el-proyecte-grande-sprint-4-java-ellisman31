import './Header.css';
import { Link } from "react-router-dom";

function Header(props) {

    const logout = async () => {
        await fetch('http://localhost:8000/api/logout', {
            method: 'POST',
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            credentials: 'include',
        });
    }

    let menu;
    const loggedUserName = props.loggedUser.name;

    //pass unique id
    
    if (loggedUserName != '') {
        menu = (
        <ul className="navBar">
            <Link className="navbar-element" to="/profile/3">Profile</Link>
            <Link className="navbar-element" to="/login">Logout</Link>
        </ul>
        )
    }
    else {
        menu = (
        <ul className="navBar">
            <Link className="navbar-element" to="/login">Login</Link>
        </ul> 
        )
    }

    return (
        <div className="nav">
            <div className="logo">
                <Link to="/">{props.title}</Link>
            </div>
            <div className="logged-user">
                <p>Logged user: {loggedUserName}</p>
            </div>
            {menu}
        </div>
    )
}

Header.defaultProps = {
    title: "Special Department",
}

export default Header
