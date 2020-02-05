import React, { useState, useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import GiphyImage from './GiphyImage.js';

function App () {
    const [movie, setMovie] = useState({});
    const [selectedMovie, setSelectedMovie] = useState("");
    const [selectedMoviePlot, setSelectedMoviePlot] = useState("");

    useEffect(() => {
        fetch('/api/movie')
            .then(response => response.json())
            .then(data => setMovie({data}));
    },[]);


    function handleClick() {
        const mov = movie.data[Math.floor(Math.random()*movie.data.length)];
        console.log(mov)
        setSelectedMovie(mov)
        if(typeof mov !== "undefined") {
            fetch("http://omdbapi.com/?apikey=853fb793&plot=full&t=" + mov.name).then(response => response.json())
                .then(response => setSelectedMoviePlot(response.Plot))
        }
    }


    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <h1>Random Movie Reviews</h1>
            </header>
            <br/>
            <br/>
            <br/>
            <button className="button"  onClick={() => handleClick()} >Get random movie review!</button>
            <h2>{selectedMovie.name}</h2>
            <GiphyImage name={selectedMovie.name}/>
            <p>{selectedMoviePlot}</p>
        </div>
    )
}

export default App;
