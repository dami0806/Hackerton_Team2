import React from 'react';
import styled from 'styled-components';
import '../App.css';

const Logo = styled.header`
  font-size: 50px;
  font-weight: bolder;
  margin: 3rem;
  font-family: 'Dancing Script', serif;
`;

function Header() {
  return (
    <div>
      <Logo>OurStory</Logo>
    </div>
  );
}

export default Header;
