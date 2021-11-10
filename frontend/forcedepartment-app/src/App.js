import './App.css';
import Main from './components/Main';
import About from './components/About';
import Register from './components/register/Register'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/about_us" element={<About/>}/>
                <Route path="/registration" element={<Register/>}/>
            </Routes>
        </Router>
    );
}

export default App;
