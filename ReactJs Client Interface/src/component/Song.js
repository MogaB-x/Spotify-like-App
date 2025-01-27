import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Song() {
  const [songs, setSongs] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('http://localhost:8080/songs');
        setSongs(res.data);
      } catch (error) {
        console.log(error);
      }
    }
    fetchData();
  }, []);

  return (
    <div>
      <h2>Song List</h2>
      {songs.length > 0 ?  (
      <table>
        <thead>
          <th>Id</th>
          <th>Name</th>
          <th>Genre</th>
          <th>Year</th>
          <th>Type</th>
          <th>Parent</th>
        </thead>
        <tbody>
        {songs.map((song, index) => (
          <tr key={index}>
            <td>{song.id}</td>
            <td>{song.songname}</td>
            <td>{song.gen}</td>
            <td>{song.year}</td>
            <td>{song.type}</td>
            <td>{song.parent}</td>
          </tr>
        ))}
        </tbody>
      </table>
      ) : (
        <div>loading ...</div>
      )}
      </div>
  );
}

export default Song;