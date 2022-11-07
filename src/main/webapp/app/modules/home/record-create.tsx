import React from 'react';
import {Button, Col, Row} from "reactstrap";
import {ValidatedField, ValidatedForm} from 'react-jhipster';
import {Link, useNavigate} from "react-router-dom";
import {useAppDispatch, useAppSelector} from "app/config/store";
import {createRecord} from "app/modules/home/home.reducer";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export const RecordCreateForm = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const isInvalid = false;
  const creating = useAppSelector(state => state.home.creating);
  const record = useAppSelector(state => state.home.createRecord);

  const handleClose = () => {
    navigate('/');
  };

  const saveUser = value => {
    console.log("Values", value);
    dispatch(createRecord(value));
    handleClose();
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h1>Создать или отредактировать пользователя</h1>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          <ValidatedForm onSubmit={saveUser} defaultValues={record}>
            <ValidatedField type="select" name="person" multiple label="Кто?">
              {["Тая", "Юля", "Тима", "Артём"].map(person => (
                <option value={person} key={person}>
                  {person}
                </option>
              ))}
            </ValidatedField>

            <ValidatedField type="select" name="food" multiple label="Еда">
              {["Royal Canin", "Sheby", "Курочка"].map(food => (
                <option value={food} key={food}>
                  {food}
                </option>
              ))}
            </ValidatedField>

            <ValidatedField
              type="number"
              name="foodGiven"
              label="Вес"
              validate={{
                required: {
                  value: true,
                  message: 'Необходимо ввести вес еды.',
                }
              }}
            />
            <Button tag={Link} to="/" replace color="info">
              <FontAwesomeIcon icon="arrow-left"/>
              &nbsp;
              <span className="d-none d-md-inline">Назад</span>
            </Button>
            &nbsp;
            <Button color="primary" type="submit" disabled={isInvalid || creating}>
              <FontAwesomeIcon icon="save"/>
              &nbsp; Сохранить
            </Button>
          </ValidatedForm>
        </Col>
      </Row>
    </div>
  )

}
