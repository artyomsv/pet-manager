import './home.scss';

import React, {useEffect, useState} from 'react';

import {Button, Card, CardText, CardTitle, Col, Container, Row} from 'reactstrap';

import {Record} from './record';

import {useAppDispatch, useAppSelector} from 'app/config/store';
import {getCatRecords} from "app/modules/home/home.reducer";
import {Link, useNavigate} from "react-router-dom";

export const Home = () => {

  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(getCatRecords('2022-11-07'));
  }, []);

  const [date, setDate] = useState(() => new Date().toLocaleDateString('en-CH'));
  const isLoading = useAppSelector(state => state.home.loading);
  const records = useAppSelector(state => state.home.records);

  return (
    <Container>

      <Row xs="2">
        <Col style={{textAlign: "left", fontSize: "2em"}}>
          {date}
        </Col>
        <Col style={{textAlign: "right", fontSize: "2em"}}>
          {records.map(it => it.weight).reduce((sum, num) => sum + num, 0)} gr
        </Col>
      </Row>

      {!isLoading &&records.map((record, index) => {
        return (
          <Row style={{paddingTop: "1em", paddingBottom: records.length == index + 1 ? "1em" : "0em"}}>
            <Col fluid="sm" lg="12">
              <Card body>
                <CardTitle tag="h5">
                  {record.time}
                </CardTitle>
                <CardText>
                  <Record key={record.id} record={record}/>
                </CardText>
              </Card>
            </Col>
          </Row>
        )
      })}
      <Row>
        <Col fluid="sm" lg="12" style={{display: "flex", justifyContent: "center", alignItems: "center"}}>
          <Button size="lg" color="primary"  tag={Link} to="/create">+</Button>
        </Col>
      </Row>
    </Container>

  );
};

export default Home;
