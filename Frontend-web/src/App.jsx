import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Main from './Pages/Main';
import Story from './Pages/Story';
import Test1 from './Pages/Test1';
import Test2 from './Pages/Test2';

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/Story' element={<Story />} />
          <Route path='/Test1' element={<Test1 />} />
          <Route path='/Test2' element={<Test2 />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
