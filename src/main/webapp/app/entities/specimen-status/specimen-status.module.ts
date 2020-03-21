import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { SpecimenStatusComponent } from './specimen-status.component';
import { SpecimenStatusDetailComponent } from './specimen-status-detail.component';
import { SpecimenStatusUpdateComponent } from './specimen-status-update.component';
import { SpecimenStatusDeleteDialogComponent } from './specimen-status-delete-dialog.component';
import { specimenStatusRoute } from './specimen-status.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(specimenStatusRoute)],
  declarations: [
    SpecimenStatusComponent,
    SpecimenStatusDetailComponent,
    SpecimenStatusUpdateComponent,
    SpecimenStatusDeleteDialogComponent
  ],
  entryComponents: [SpecimenStatusDeleteDialogComponent]
})
export class RockstockSpecimenStatusModule {}
