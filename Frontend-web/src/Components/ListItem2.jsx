import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const ItemWrap = styled.div`
  width: 17.5rem;
  height: 4rem;
  border: 1px solid black;
  border-radius: 10rem;
  margin: 1rem 0;
  justify-content: center;
  align-items: center;
  display: flex;
  font-size: 20px;
`;

const Title = styled.div`
  width: 14rem;
  height: 4rem;
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
`;

const LinkBtn = styled.button`
  width: 2rem;
  height: 2rem;
  margin-left: auto;
  margin-right: 2rem;
  border-radius: 100%;
  background-color: lightpink;
  border: none;
`;

function ListItem2() {
  return (
    <div>
      <ItemWrap>
        <Title>
          2022년 11월 26일
          <br />
          생일
        </Title>
        <LinkBtn>
          {' '}
          <Link
            to={{ pathname: '/Test2' }}
            style={{ color: 'inherit', textDecoration: 'inherit' }}
          >
            Go
          </Link>
        </LinkBtn>
      </ItemWrap>
    </div>
  );
}

export default ListItem2;
