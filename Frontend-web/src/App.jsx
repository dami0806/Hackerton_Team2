import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Main from './Pages/Main';
import Story from './Pages/Story';

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/Story' element={<Story />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
