import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Header from '../Components/Header';
import Couple1 from '../Components/Couple1.jpeg';

const MainWrap = styled.div`
  border: 1px solid black;
  width: 70rem;
  height: 40rem;
  margin: auto;
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  font-family: 'Cute Font', serif;
`;

const TitleWrap = styled.div`
  width: 30rem;
  height: 5rem;
  justify-content: center;
  align-items: center;
  display: flex;
  font-size: 30px;
  font-family: 'Cute Font', serif;
`;

const Title = styled.div`
  font-size: 30px;
  justify-content: center;
  align-items: center;
  display: flex;
  margin-left: 1rem;
`;

const CenterWrap = styled.div`
  width: 50rem;
  height: 30rem;
  justify-content: center;
  align-items: center;
  display: flex;
`;

const SubTitleWrap = styled.div`
  width: 50rem;
  height: 4rem;
  justify-content: center;
  align-items: center;
  display: flex;
`;

const SubTitle = styled.div`
  width: 10rem;
  height: 3rem;
  justify-content: center;
  align-items: center;
  display: flex;
  font-size: 30px;
  margin: 0 5.5rem;
`;

const PictureWrap = styled.div`
  width: 20rem;
  height: 25rem;
  margin-right: 2rem;
  justify-content: center;
  align-items: center;
  display: flex;
  background-image: url(${Couple1});
  background-position: center;
  background-size: 20rem;
  background-repeat: no-repeat;
`;

const UploadImage = styled.img`
  width: 20rem;
  height: 30rem;
  object-fit: scale-down;
`;

const WriteWrap = styled.div`
  border: 1px solid black;
  width: 20rem;
  height: 25rem;
  font-size: 50px;
  white-space: pre-wrap;
`;

const BtnWrap = styled.div`
  width: 20rem;
  height: 5rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const PictureUpload = styled.input`
  display: none;
`;

const PictureBtn = styled.label`
  width: 5rem;
  height: 3rem;
  background-color: lightpink;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10rem;
  border: none;
  margin: 0 1rem;
`;

const Btn = styled.button`
  width: 5rem;
  height: 3rem;
  background-color: lightpink;
  border-radius: 10rem;
  border: none;
  margin-right: 1rem;
  font-family: 'Cute Font', serif;
  font-size: 20px;
`;

function Test1() {
  const [fileImage, setFileImage] = useState('');
  const [fileIma, setFileIma] = useState('');
  const [visible, setVisible] = useState(false);

  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    URL.revokeObjectURL(fileIma);
    setFileIma('');
    setFileImage('');
    setVisible(false);
  };

  const handleSelect = async (e) => {
    const file = e.target.files[0];
    const Image = URL.createObjectURL(file);
    setFileIma(Image);
    setFileImage(file);
    setVisible(true);
  };

  return (
    <div>
      <Header />
      <MainWrap>
        <TitleWrap>
          제목 : <Title>100일</Title>
        </TitleWrap>
        <TitleWrap>날짜 : 2022년 11월 11일</TitleWrap>
        <SubTitleWrap>
          <SubTitle>사진</SubTitle>
          <SubTitle>내용</SubTitle>
        </SubTitleWrap>
        <CenterWrap>
          <PictureWrap />
          <WriteWrap>우리 100일이에요!</WriteWrap>
        </CenterWrap>
        <BtnWrap>
          <Btn>
            <Link
              to={{ pathname: '/' }}
              style={{ color: 'inherit', textDecoration: 'inherit' }}
            >
              메인
            </Link>
          </Btn>
        </BtnWrap>
      </MainWrap>
    </div>
  );
}

export default Test1;
