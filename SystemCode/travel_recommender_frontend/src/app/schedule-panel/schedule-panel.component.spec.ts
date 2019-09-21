import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulePanelComponent } from './schedule-panel.component';

describe('SchedulePanelComponent', () => {
  let component: SchedulePanelComponent;
  let fixture: ComponentFixture<SchedulePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedulePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
