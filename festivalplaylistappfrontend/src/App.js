import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import SpotifyLogin from "./components/SpotifyLogin.js";
import Home from "./components/Home.js";
import SearchResultPage from './components/SearchResultPage.js';

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element = {<SpotifyLogin/>} />
          <Route path="/home" element = {<Home />} />
          <Route path="/search-result" element = {<SearchResultPage />} /> 
        </Routes>
    </Router>
  )
}

export default App; // exports the App component avaliable for import in other files
