import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Main from './Pages/Main';
import Story from './Pages/Story';
import Test1 from './Pages/Test1';

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/Story' element={<Story />} />
          <Route path='/Test1' element={<Test1 />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
