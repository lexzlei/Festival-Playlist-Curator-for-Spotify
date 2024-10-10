import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import SpotifyLogin from "./components/SpotifyLogin.js";
import Home from "./components/Home.js";
import SearchResultPage from './components/SearchResultPage.js';
import PlaylistCreatedPage from './components/PlaylistCreatedPage.js';
import Header from './components/Header.js';

function App() {
  return (
    <Router>
      <div className='App'>
        <Header />
        <Routes>
          <Route path="/" element = {<SpotifyLogin/>} />
          <Route path="/home" element = {<Home />} />
          <Route path="/search-result" element = {<SearchResultPage />} /> 
          <Route path="/playlist-created" element = {<PlaylistCreatedPage />} />
        </Routes>
      </div>
      <footer>&copy; 2024 Festbeats</footer>
    </Router>
  )
}

export default App; // exports the App component avaliable for import in other files
