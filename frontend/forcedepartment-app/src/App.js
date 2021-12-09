import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Main from './components/main_page/Main';
import About from './components/about/About';
import Profile from './components/profile/Profile';
import Register from './components/register/Register'
import Login from './components/login/Login'

function App() {

    return (
            <Router>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/about_us" element={<About/>}/>
                    <Route path="/registration" element={<Register/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/profile/:userId" element={<Profile/>}/>
                </Routes>
            </Router>
    );
}

export default App;
