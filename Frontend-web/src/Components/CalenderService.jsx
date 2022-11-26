import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import moment from 'moment';
import Toolbar from './Toolbar';
import ListItem from '../Components/ListItem';

const events = [
  {
    title: '100일',
    allDay: true,
    start: new Date(2022, 10, 11),
    end: new Date(2022, 10, 11),
  },
];

const MainWrap = styled.div`
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  .rbc-calendar {
    width: 50rem;
    box-shadow: 0px 0px 10px #000;
  }
  .rbc-event {
    background-color: pink;
  }
`;

const CenterWrap = styled.div`
  width: full;
  height: 550px;
  justify-content: center;
  align-items: center;
  display: flex;
`;

const ListWrap = styled.div`
  border: 1px solid black;
  width: 30rem;
  height: 500px;
  border-radius: 10%;
  justify-content: top;
  align-items: center;
  display: flex;
  flex-direction: column;
`;

const ListTitle = styled.div`
  width: 10rem;
  height: 4rem;
  margin-top: 3rem;
  justify-content: center;
  align-items: center;
  display: flex;
  font-size: 30px;
  font-weight: bold;
`;

const List = styled.ul`
  width: 20rem;
  height: 300px;
  border: 1px solid black;
  overflow: auto;
  list-style: none;
`;

const AddWrap = styled.div`
  justify-content: center;
  align-items: center;
  display: flex;
  width: 35rem;
`;

const DateWrap = styled.div`
  width: 70rem;
  height: 40rem;
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
`;

const SubTitleWrap = styled.div`
  width: 50rem;
  height: 1rem;
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
  font-size: 20px;
  margin: 0 6rem;
`;

const Container = styled.div`
  width: 50rem;
  height: 30rem;
  justify-content: center;
  align-items: center;
  display: flex;
`;

const PictureWrap = styled.div`
  border: 1px solid black;
  width: 20rem;
  height: 25rem;
  margin-right: 2rem;
  justify-content: center;
  align-items: center;
  display: flex;
`;

const UploadImage = styled.img`
  width: 20rem;
  height: 30rem;
  object-fit: scale-down;
`;

const WriteWrap = styled.textarea`
  border: 1px solid black;
  width: 20rem;
  height: 25rem;
  font-size: 20px;
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
`;

const AddBtn = styled.button`
  width: 13rem;
  height: 2rem;
  background-color: lightpink;
  border-radius: 10rem;
  border: none;
  margin-right: 1rem;
`;

function CalenderService() {
  const [value, onChange] = useState(new Date());
  const [newEvent, setNewEvent] = useState({ title: '', end: '' });
  const [allEvents, setAllEvents] = useState(events);

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

  const postData = async (e) => {
    try {
      const response = await axios.post('http://localhost:8000/api/v1/', {
        id: '1',
        start_date: '2022-11-05',
        end_date: '2022-11-05',
        title: setNewEvent({ ...newEvent, title: e.target.value }),
        text: '안녕하세요.',
      });
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  function handleAddEvent() {
    for (let i = 0; i < allEvents; i++) {
      const d1 = new Date(allEvents[i].start);
      const d2 = new Date(newEvent.start);
      const d3 = new Date(allEvents[i].end);
      const d4 = new Date(newEvent.end);

      if ((d1 <= d2 && d2 <= d3) || (d1 < d4 && d4 <= d3)) {
        alert('CRASH');
        break;
      }
    }
    setAllEvents([...allEvents, newEvent]);
  }

  moment.locale('ko-KR');
  const localizer = momentLocalizer(moment);

  return (
    <div>
      <MainWrap>
        <CenterWrap>
          <Calendar
            localizer={localizer}
            events={allEvents}
            startAccessor='start'
            endAccessor='end'
            style={{ height: 500, margin: '50px' }}
            components={{
              toolbar: Toolbar,
            }}
            onChange={onChange}
            value={value}
          />
          <ListWrap>
            <ListTitle>OurStory</ListTitle>
            <List>
              <ListItem />
            </List>
          </ListWrap>
        </CenterWrap>
        <AddWrap>
          <div>
            <input
              type='text'
              placeholder='Title'
              style={{ width: '100px', marginRight: '10px' }}
              value={newEvent.title}
              onChange={(e) =>
                setNewEvent({ ...newEvent, title: e.target.value })
              }
            />
          </div>
          <DatePicker
            placeholderText='Start Date'
            selected={newEvent.start}
            onChange={(start) => setNewEvent({ ...newEvent, start })}
          />
          <DatePicker
            placeholderText='End Date'
            selected={newEvent.end}
            onChange={(end) => setNewEvent({ ...newEvent, end })}
          />
          <AddBtn
            onClick={() => {
              handleAddEvent();
            }}
            onChange={postData()}
          >
            스토리 추가
          </AddBtn>
        </AddWrap>
        <DateWrap>
          <SubTitleWrap>
            <SubTitle>사진</SubTitle>
            <SubTitle>내용</SubTitle>
          </SubTitleWrap>
          <Container>
            <PictureWrap>
              {fileIma && (
                <UploadImage alt='sample' id='sample' src={fileIma} />
              )}
            </PictureWrap>
            <WriteWrap />
          </Container>
          <BtnWrap>
            <PictureUpload
              id='name'
              name='imgUpload'
              type='file'
              accept='image/*'
              onChange={handleSelect}
            />
            <PictureBtn htmlFor='name'>사진 등록</PictureBtn>
            {visible && <Btn onClick={() => deleteFileImage()}>사진 삭제</Btn>}
          </BtnWrap>
        </DateWrap>
      </MainWrap>
    </div>
  );
}

export default CalenderService;
