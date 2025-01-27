import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Artist() {
  const [artists, setArtists] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('http://localhost:8080/artists');
        setArtists(res.data);
      } catch (error) {
        console.log(error);
      }
    }
    fetchData();
  }, []);

  return (
    <div>
      <h2>Artist List</h2>
      {artists.length > 0 ? 
      (
      <table>
        <thead>
          <th>Id</th>
          <th>Name</th>
        </thead>

        <tbody>
        {artists.map((artist, index) => (
          <tr key={index}>
            <td>{artist.id}</td>
            <td>{artist.name}</td>
          </tr>
        ))}
        </tbody>
      </table>
      ) : (
        <div>Loading ...</div>
      )}
      </div>
  );
}

export default Artist;