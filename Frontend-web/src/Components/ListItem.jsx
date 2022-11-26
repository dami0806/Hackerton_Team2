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

function ListItem() {
  return (
    <div>
      <ItemWrap>
        2022년 11월 11일
        <LinkBtn>
          {' '}
          <Link
            to={{ pathname: '/Test1' }}
            style={{ color: 'inherit', textDecoration: 'inherit' }}
          >
            Go
          </Link>
        </LinkBtn>
      </ItemWrap>
    </div>
  );
}

export default ListItem;
