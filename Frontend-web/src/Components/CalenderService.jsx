import React, { useState } from 'react';
import styled from 'styled-components';
import 'react-calendar/dist/Calendar.css';
import Calender from 'react-calendar';

const MainWrap = styled.div`
  justify-content: center;
  align-items: center;
  display: flex;
  .react-calendar {
    width: 70rem;
  }
`;

function CalenderService() {
  const [value, onChange] = useState(new Date());
  return (
    <div>
      <MainWrap>
        <Calender onChange={onChange} value={value} />
      </MainWrap>
    </div>
  );
}

export default CalenderService;
