import './App.css';
import Main from './components/Main';
import About from './components/About';
import Profile from './components/Profile';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/about_us" element={<About />} />
        <Route path="/profile/:userId" element={<Profile />}/> 
      </Routes>
    </Router>
  );
}

export default App;
