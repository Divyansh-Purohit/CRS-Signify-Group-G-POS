import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignProfessorComponent } from './assign-professor.component';

describe('AssignProfessorComponent', () => {
  let component: AssignProfessorComponent;
  let fixture: ComponentFixture<AssignProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignProfessorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
